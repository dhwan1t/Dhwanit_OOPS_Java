# Course Servlet/JSP App - Run Guide (Tomcat 11 + IntelliJ)

This guide runs the app on **Tomcat 10/11** with **IntelliJ build output** and **SQLite JDBC**.

## 1) Prerequisites

- JDK 17+ (or your project JDK)
- Apache Tomcat 10.1+ or 11.x
- IntelliJ IDEA
- JARs:
  - `sqlite-jdbc-<version>.jar`
  - `jakarta.servlet.jsp.jstl-api-<version>.jar`
  - `jakarta.servlet.jsp.jstl-<version>.jar`

## 2) Build in IntelliJ (must match Tomcat Java)

1. Open project: `.../Uni/OOPS_Lab/com`
2. Set **Project SDK = 21** and **Project language level = 21**  
   (Tomcat is running on Java 21 on your machine.)
3. In **Settings -> Build, Execution, Deployment -> Compiler -> Java Compiler**, set **Target bytecode version = 21**.
4. Click **Build -> Build Project**
5. Confirm compiled classes exist under:
   - `com/out/production/com/Uni/OOPS_Lab/com/course/*.class`
6. If you changed servlet mapping code/config, **build again before deploy** so `CourseServlet.class` is refreshed.
7. Verify class version is Java 21:
   ```bash
   javap -verbose -classpath "out/production/com" Uni.OOPS_Lab.com.course.CourseServlet | grep "major version"
   ```
   Expected: `major version: 65`

## 3) Deploy to Tomcat as `/app`

Use terminal:

```bash
TOMCAT="/Users/himishavyas/Downloads/apache-tomcat-11.0.21"
PROJECT="/Users/himishavyas/Documents/Code Files/Java/src/Uni/OOPS_Lab/com"
APP="$TOMCAT/webapps/app"

# clean old deployment
rm -rf "$APP"
rm -rf "$TOMCAT/work/Catalina/localhost/app"
mkdir -p "$APP/WEB-INF/classes" "$APP/WEB-INF/lib"

# copy JSP + web.xml
cp "$PROJECT/web/index.jsp" "$APP/"
cp "$PROJECT/web/view.jsp" "$APP/"
cp "$PROJECT/web/WEB-INF/web.xml" "$APP/WEB-INF/"

# IMPORTANT: copy full package tree, not flat .class files
cp -R "$PROJECT/out/production/com/Uni" "$APP/WEB-INF/classes/"

# add runtime dependencies
cp "/Users/himishavyas/Downloads/sqlite-jdbc-3.53.0.0-natives-mac.jar" "$APP/WEB-INF/lib/"
curl -fL "https://repo1.maven.org/maven2/jakarta/servlet/jsp/jstl/jakarta.servlet.jsp.jstl-api/3.0.2/jakarta.servlet.jsp.jstl-api-3.0.2.jar" -o "$APP/WEB-INF/lib/jakarta.servlet.jsp.jstl-api-3.0.2.jar"
curl -fL "https://repo1.maven.org/maven2/org/glassfish/web/jakarta.servlet.jsp.jstl/3.0.1/jakarta.servlet.jsp.jstl-3.0.1.jar" -o "$APP/WEB-INF/lib/jakarta.servlet.jsp.jstl-3.0.1.jar"

# if Tomcat is already running, stop it first
sh "$TOMCAT/bin/shutdown.sh" || true
```

## 4) Run Tomcat in foreground (recommended)

```bash
cd "/Users/himishavyas/Downloads/apache-tomcat-11.0.21/bin"
sh ./catalina.sh run
```

Keep this terminal open while testing.  
If you press `Ctrl+C`, Tomcat stops.

## 5) Required deployed structure

```text
app/
├── index.jsp
├── view.jsp
└── WEB-INF/
    ├── classes/
    │   └── Uni/OOPS_Lab/com/course/
    │       ├── CourseServlet.class
    │       ├── DBConnection.class
    │       ├── model$Course.class
    │       ├── service$CourseService.class
    │       └── ...
    ├── lib/
    │   ├── sqlite-jdbc-<version>.jar
    │   ├── jakarta.servlet.jsp.jstl-api-<version>.jar
    │   └── jakarta.servlet.jsp.jstl-<version>.jar
    └── web.xml
```

## 6) Access URLs

- Home: `http://localhost:8080/app/`
- Servlet: `http://localhost:8080/app/CourseServlet`

## 7) Debug check (already added in servlet)

Tomcat logs should show:

- `[CourseServlet] init() loaded successfully.`
- `[CourseServlet] doGet() called.`
- `[CourseServlet] doPost() called.`

If you still get 404/500, verify:

1. App folder is exactly `webapps/app`
2. `CourseServlet.class` path is exactly:
   - `WEB-INF/classes/Uni/OOPS_Lab/com/course/CourseServlet.class`
3. You copied the full `Uni/...` package tree (not only top-level `.class`)

## 8) Quick smoke test commands

```bash
curl -i http://localhost:8080/app/
curl -i http://localhost:8080/app/CourseServlet
```

Expected:
- `/app/` -> `HTTP/1.1 200`
- `/app/CourseServlet` -> `HTTP/1.1 200` (or app-level error, but servlet must load)

## 9) Critical troubleshooting from your current logs

1. **Servlet mapping missing or inconsistent**
   - Symptom: `/app/` works but `/app/CourseServlet` returns 404.
   - Fix in this repo: servlet is mapped explicitly in `WEB-INF/web.xml` to `/CourseServlet`.
   - Keep mapping in one place (web.xml) and redeploy.

2. **Java version mismatch**
   - Error: `UnsupportedClassVersionError ... class file version 68.0 ... recognizes up to 65.0`
   - Cause: IntelliJ compiled with Java 24, Tomcat ran with Java 21.
   - Fix: compile to Java 21 (step 2 above), or run Tomcat on Java 24.
   - Fast fallback compile command (forces Java 21 output):
     ```bash
     /Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home/bin/javac --release 21 -cp "/Users/himishavyas/Downloads/apache-tomcat-11.0.21/lib/servlet-api.jar:/Users/himishavyas/Downloads/sqlite-jdbc-3.53.0.0-natives-mac.jar" -d "/Users/himishavyas/Documents/Code Files/Java/src/Uni/OOPS_Lab/com/out/production/com" /Users/himishavyas/Documents/Code\ Files/Java/src/Uni/OOPS_Lab/com/course/*.java
     ```

3. **JARs missing in `WEB-INF/lib`**
   - If `/app/CourseServlet` fails at runtime, ensure these are present:
     - `sqlite-jdbc-<version>.jar`
     - `jakarta.servlet.jsp.jstl-api-<version>.jar`
     - `jakarta.servlet.jsp.jstl-<version>.jar`

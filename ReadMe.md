# Pageditain Project - Cucumber/TestNG/Selenium

This project uses Cucumber with TestNG and Selenium, built with Maven. Below are concise instructions on how to run the tests, where to change the Cucumber tags, and where the reports are generated.

## Quick Links
- Test runner: [src/test/java/runners/TestRunner.java](src/test/java/runners/TestRunner.java)
- Main config: [Config.properties](Config.properties)
- Environment configs: [src/test/resources/PropertiesFolder](src/test/resources/PropertiesFolder)
- Feature files: [src/test/resources/features](src/test/resources/features)
- Cucumber hooks (results setup): [src/test/java/stepDefs/Hook.java](src/test/java/stepDefs/Hook.java)
- Report paths utility: [src/main/java/framework/PathUtils.java](src/main/java/framework/PathUtils.java)

## Quick Start
- Run tests: `mvn clean test`
- Run all features: set `features = "./src/test/resources/features"` in `src/test/java/runners/TestRunner.java`
- Common tag filters (in `@CucumberOptions`):
  - Only Smoke: `tags = "@Smoke"`
  - Smoke or Regression: `tags = "@Smoke or @Regression"`
  - Smoke and UI: `tags = "@Smoke and @UI"`
  - Exclude WIP: `tags = "not @Wip"`
- Open reports after run:
  - Cucumber HTML: `CucumberHTMLReport.html` (project root)
  - Extent: `Results/<timestamp>/ExtentReports.html`

## Prerequisites
- Java 21 (as configured in the Maven Compiler plugin)
- Maven 3.8+

## Where to Run the Code
- From the project root: `D:\Trainings_Classes\Pageditain_Project`
- Command line:
  - `mvn clean test`
- IDE alternative:
  - Run the TestNG runner class: `src/test/java/runners/TestRunner.java`

Maven Surefire is configured to include `**/TestRunner.java` (see `pom.xml`).

## Where to Change Cucumber Tags
- Edit the `@CucumberOptions` in `src/test/java/runners/TestRunner.java`.
- Current value:
  - `tags = "@CreateCustomer or @CreateAccount"`
- Examples:
  - Run a single tag: `tags = "@Smoke"`
  - Run any of multiple: `tags = "@Smoke or @Regression"`
  - Run all of multiple: `tags = "@Smoke and @UI"`
  - Exclude a tag: `tags = "not @Wip"`
- You can also broaden the feature path to run all features:
  - From: `features = "./src/test/resources/features/SampleFeature.feature"`
  - To:   `features = "./src/test/resources/features"`

File reference: `src/test/java/runners/TestRunner.java`

## Where Reports Are Generated
- Cucumber built-in reports (configured via `plugin` in the runner) are placed at the project root:
  - `CucumberHTMLReport.html`
  - `CucumberJSONReport.json`
  - `CucumberJSONReport.xml`
  - Source: `src/test/java/runners/TestRunner.java`

- ExtentReports are created per run inside a timestamped Results folder:
  - Location pattern: `Results/<timestamp>/ExtentReports.html`
  - The timestamped folder is generated at start of a run, and older runs are backed up into `Results/Old Results`.
  - Sources:
    - Results folder handling: `src/test/java/stepDefs/Hook.java`
    - Paths: `src/main/java/framework/PathUtils.java`
    - Extent init: `src/main/java/framework/ExtentReportUtil.java`

- Step logs and screenshots:
  - Steps log per scenario: `Results/<timestamp>/StepsLog_<TestCase>.txt`
  - Run-scoped screenshots: `Results/<timestamp>/Screenshots/`
  - Global (by-date) screenshots: `Screenshots/<dd-MM-yyyy>/`
  - Source: `src/main/java/framework/PathUtils.java`

## Notes
- To run all `.feature` files under `src/test/resources/features`, update the `features` path as shown above.
- Reports are flushed at the end of each scenario; consolidated vs. individual ExtentReports is driven by the `ConsolidatedOrIndividualReport` property.

## Troubleshooting
- Java version mismatch: ensure JDK 21 is active. Check with `mvn -v` and install/configure Java 21 if needed.
- No tests run:
  - Verify the `features` path and that your scenarios match the `tags` filter.
  - Temporarily remove `tags` or set a broad filter (e.g., `"@Smoke or @Regression"`).
  - You can force-run the runner with: `mvn -Dtest=runners.TestRunner test`.
- Browser won’t launch:
  - Check `Config.properties` values for `Browser`, `Headless`, and `Incognito`.
  - Ensure the chosen browser is installed on the machine.
- Reports not generated:
  - Cucumber reports should appear at project root: `CucumberHTMLReport.html`, `.json`, `.xml` (runner `plugin` driven).
  - Extent report should be at: `Results/<timestamp>/ExtentReports.html`.
  - Make sure you are running `src/test/java/runners/TestRunner.java` so `stepDefs.Hook` executes (`@BeforeAll` creates the Results folder).
  - If the `Results` folder looks corrupted, delete it and re-run; it will be recreated.
- Too many old results: old runs are backed up to `Results/Old Results`. It’s safe to clean up older folders.
- Browser not closing: set `BrowserClose=true` in `Config.properties`.
- Environment properties missing: ensure `Environment` in `Config.properties` matches one of the files in `src/test/resources/PropertiesFolder` (e.g., `QAConfig.properties`, `StagingConfig.properties`, `UATConfig.properties`, `ProdConfig.properties`).

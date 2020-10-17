dependencies {
    api(project(":domain"))
}

tasks.register("testReport") {
    val test by tasks
    val jacocoTestReport by tasks
    val jacocoTestCoverageVerification by tasks

    dependsOn(test, jacocoTestReport, jacocoTestCoverageVerification)
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {

            limit {
                counter = "INSTRUCTION"
                value = "COVEREDRATIO"
                minimum = "0.90".toBigDecimal()
            }

            limit {
                counter = "BRANCH"
                value = "COVEREDRATIO"
                minimum = "0.90".toBigDecimal()
            }

            excludes = listOf(
                "*.test.*"
            )
        }
    }
}

tasks.jacocoTestReport {
    reports {
        isEnabled = true
        xml.isEnabled = true
        csv.isEnabled = true
        html.destination = file("$buildDir/reports/jacoco")
    }
}
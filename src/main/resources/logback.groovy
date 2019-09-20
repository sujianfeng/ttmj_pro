//
// Built on Mon Jan 22 15:29:26 CET 2018 by logback-translator
// For more information on configuration files in Groovy
// please see http://logback.qos.ch/manual/groovy.html

// For assistance related to this tool or configuration files
// in general, please contact the logback user mailing list at
//    http://qos.ch/mailman/listinfo/logback-user

// For professional support please see
//   http://www.qos.ch/shop/products/professionalSupport

import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.classic.filter.ThresholdFilter

scan("10 seconds")
def LOG_PATH = "/data/logs/ttmj"
appender("INFO_FILE", RollingFileAppender) {
    file = "${LOG_PATH}/info.log"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${LOG_PATH}/info-%d{yyyyMMdd}.log.%i"
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = "500MB"
        }
        maxHistory = 1000
    }
    layout(PatternLayout) {
        pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} -%msg%n"
    }
}
appender("ERROR_FILE", RollingFileAppender) {
    filter(ThresholdFilter) {
        level = ERROR
    }
    file = "${LOG_PATH}/error.log"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${LOG_PATH}/error-%d{yyyyMMdd}.log.%i"
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = "500MB"
        }
        maxHistory = 1000
    }
    layout(PatternLayout) {
        pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} -%msg%n"
    }
}
root(INFO, ["INFO_FILE", "ERROR_FILE"])
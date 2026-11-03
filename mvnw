#!/bin/sh

set -eu

MAVEN_PROJECTBASEDIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd -P)"
WRAPPER_JAR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"

if [ -n "${JAVA_HOME:-}" ]; then
    JAVACMD="$JAVA_HOME/bin/java"
elif command -v java >/dev/null 2>&1; then
    JAVACMD="$(command -v java)"
else
    echo "JAVA_HOME must point to a Java installation, or java must be available on PATH." >&2
    exit 1
fi

if [ ! -f "$WRAPPER_JAR" ]; then
    echo "Maven Wrapper JAR is missing: $WRAPPER_JAR" >&2
    exit 1
fi

exec "$JAVACMD" \
    -classpath "$WRAPPER_JAR" \
    -Dmaven.multiModuleProjectDirectory="$MAVEN_PROJECTBASEDIR" \
    org.apache.maven.wrapper.MavenWrapperMain "$@"

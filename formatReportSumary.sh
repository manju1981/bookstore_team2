#!/usr/bin/env bash

echo "# Coverage Report Summary"
head -1 build/reports/jacoco/test/jacocoTestReport.csv | sed 's/,/|/g'
echo "---|---|---|---|--|---|---|---|---|---|---|---|---"
tail -n +2 build/reports/jacoco/test/jacocoTestReport.csv | sed 's/,/|/g'

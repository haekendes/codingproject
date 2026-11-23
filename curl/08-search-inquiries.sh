#!/bin/bash

# 1. Get all inquiries
curl -X GET "http://localhost:8080/inquiries" \
  -H "Accept: application/json"

echo -e "\n\n"

# 2. Search inquiries by status
curl -X GET "http://localhost:8080/inquiries?status=PROCESSED" \
  -H "Accept: application/json"

echo -e "\n\n"

# 3. Search inquiries by customer reference
curl -X GET "http://localhost:8080/inquiries?customerReference=CUSTOMER-123" \
  -H "Accept: application/json"

echo -e "\n\n"

# 4. Search inquiries by status AND customer reference
curl -X GET "http://localhost:8080/inquiries?status=PROCESSED&customerReference=CUSTOMER-123" \
  -H "Accept: application/json"

echo

#!/bin/bash

BASE_URL="http://localhost:8080"
INQUIRY_ID="dd10cc63-f716-4ad4-a9c8-b41033e6d549"

if [ -z "$INQUIRY_ID" ]; then
  echo "Usage: ./02-get-inquiry.sh <INQUIRY_ID>"
  exit 1
fi

curl -i "$BASE_URL/inquiries/$INQUIRY_ID"

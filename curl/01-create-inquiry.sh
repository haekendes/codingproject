
#!/bin/bash

BASE_URL="http://localhost:8080"

curl -i -X POST "$BASE_URL/inquiries" \
  -H "Content-Type: application/json" \
  -d '{
    "externalInquiryNumber": "EXT-2026-001",
    "customerReference": "CUSTOMER-123",
    "positions": [
      {
        "articleName": "Industrial Pump",
        "quantity": 2,
        "unitPrice": 149.90
      },
      {
        "articleName": "Pressure Sensor",
        "quantity": 3,
        "unitPrice": 39.50
      }
    ]
  }'

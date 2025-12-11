---
tool: employees://execute-query
---

# Employee Query Tool

## Purpose
This tool MUST be used whenever the user wants to:
- filter employees
- search employees
- query employees by conditions

Do NOT answer employee queries using text.
Always call the tool.

## Preconditions
Before calling this tool:
1. Read `employees://field-map` to know valid field names
2. Build query strictly according to `query://query-dsl`

## Input
- Type: JSON
- Schema: QueryRequest
- Defined in: query://query-dsl

### Important rules
- Only use fields defined in `employees://field-map`
- Do NOT invent field names
- Do NOT invent operators
- If user mentions a field not in field-map → ask for clarification
- The page field is zero index (start from 0)

## Output
- JSON
- Paginated list of employees
- Read-only, no side effects

## Example

User intent:
> Find employees with salary between 1500 and 2000 in Engineering

Tool call:
```json
{
  "query": {
    "bool": {
      "must": [
        {
          "range": {
            "salary": { "gte": 1500, "lte": 2000 }
          }
        },
        {
          "match": {
            "deparment_name": "Engineering"
          }
        }
      ]
    }
  },
  "limit": 5,
  "page": 0
}

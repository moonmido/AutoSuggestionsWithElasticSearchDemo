# 📦 AutoSuggestionsWithElasticSearchDemo

## 🔎 Description
A Spring Boot + Elasticsearch demo project that implements **real-time product auto-suggestions**.  
Users can type partial queries (e.g. `s`, `sa`, `iph`) and instantly receive relevant suggestions such as `samsung tv`, `iphone 11`, or `lg tv`.  

This project demonstrates:
- Custom Elasticsearch analyzers (`edge_ngram` for indexing, `standard` for searching).
- CRUD operations for products (`id`, `name`, `price`, `quantity`).
- Auto-suggestions with **Match** and **Prefix** queries.
- Clean architecture with `Service`, `Repository`, `Utils`, and `Controller` layers.

---

## ⚙️ Elasticsearch Index Configuration

Create the index with the following configuration:

```json
{
  "settings": {
    "analysis": {
      "analyzer": {
        "autocomplete_analyzer": {
          "tokenizer": "standard",
          "filter": [
            "lowercase",
            "autocomplete_filter"
          ]
        },
        "autocomplete_search_analyzer": {
          "tokenizer": "standard",
          "filter": [
            "lowercase"
          ]
        }
      },
      "filter": {
        "autocomplete_filter": {
          "type": "edge_ngram",
          "min_gram": 1,
          "max_gram": 20
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "id": {
        "type": "integer"
      },
      "name": {
        "type": "text",
        "analyzer": "autocomplete_analyzer",
        "search_analyzer": "autocomplete_search_analyzer"
      },
      "price": {
        "type": "double"
      },
      "quantity": {
        "type": "integer"
      }
    }
  }
}

```


# 🚀 Features


✅ Add new products to Elasticsearch

✅ Retrieve all stored products

✅ Auto-suggestion API (partial search queries)

✅ Spring Boot + Elasticsearch Java API Client integration

✅ Clean code with utility classes for building queries



# 📂 Project Structure

src/main/java/com/AutoSuggestionsWithElasticSearchDemo/
│

├── Controllers/

│   └── ProductController.java   # REST APIs (add, search, suggest)

│

├── Services/

│   └── EsService.java           # Elasticsearch service layer

│

├── Repositories/

│   └── ProductRepo.java         # Spring Data Elasticsearch repo

│

├── Models/

│   └── MyProduct.java           # Product entity

│

├── Utils/

│   └── EsUtil.java              # Match & Prefix query helpers




# 📌 Example API Usage
------------------------

# 1/ ➕ Add Product


POST http://localhost:8080/products/add
Content-Type: application/json

{
  "id": 1,
  "name": "samsung tv",
  "price": 1200.50,
  "quantity": 10
}


# 2/ 🔎 Get Suggestions


GET http://localhost:8080/products/get/sa



 # 3/  Response:

 [
  "samsung tv"
]

# 🛠️ Tech Stack


Java 17+

Spring Boot 3+

Spring Data Elasticsearch (Java API Client)

Elasticsearch 8+

Maven


# 📈 Future Improvements


Add fuzzy search (to tolerate typos).

Add pagination & sorting.

Dockerize Spring Boot + Elasticsearch.

Integrate Kibana dashboards for monitoring.



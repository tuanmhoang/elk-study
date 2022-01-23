# elk
Study ELK stack

This project follows a very nice template at https://github.com/deviantony/docker-elk

## 

## Reference
- [1] https://logz.io/blog/elk-stack-on-docker/
- [2] https://github.com/deviantony/docker-elk

## Steps to do

### 1. Install Elastic Search, Logtash and Kibana

Follow the template project to have the configuration for `elasticsearch`, `kibana`, and `logtash`

The version is defined at `.env`

To run, use `docker compose up -d`

### Ports to be used

- Elasticsearch (9200/9300)
- Kibana (5601) 
- Logstash (5000/5044).

#### 1. Elasticsearch

Go to http://localhost:9200 and use username password

Response
```
{
  "name" : "e8466b962940",
  "cluster_name" : "docker-cluster",
  "cluster_uuid" : "C9P19rQ8TlOyx3h1xDOH3Q",
  "version" : {
    "number" : "7.16.2",
    "build_flavor" : "default",
    "build_type" : "docker",
    "build_hash" : "2b937c44140b6559905130a8650c64dbd0879cfb",
    "build_date" : "2021-12-18T19:42:46.604893745Z",
    "build_snapshot" : false,
    "lucene_version" : "8.10.1",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}
```

To check indices

```
GET /_cat/indices?v
```

#### 2. Kibana

Go to http://localhost:5601


#### 3. Logstash


### Do something

Create JSON object that represents information about event.

Event object should include following fields: * title * event type (workshop / tech-talk) * date and time * place * description * list of sub-topics

Go to: http://localhost:5601

Go to `developement` and create object

```
POST /events/_create/1
{
  "id": "1",
	"title": "Java 1",
	"eventType": "Training",
	"dateTime": "2022-01-01",
	"place": "conference room 1",
	"description": "Java 1 training",
	"sub-topics": [
		{
			"id": "1",
			"name": "Java collection",
			"speaker": "Alice"
		},
		{
			"id": "2",
			"name": "Multithreading",
			"speaker": "Bob"
		}
	]
}
```

To get the object

```
GET /events/workshop/1
```

To delete the object

```
DELETE events
```

To be able to use curl, turn off credentials 

Use curl command

```
curl -XPUT "http://localhost:9200/events/workshop/1" -H 'Content-Type: application/json' -d'
{
  "id": "1",
	"title": "Java 1",
  "eventType": "Training",
	"dateTime": "2022-01-01",
	"place": "conference room 1",
	"description": "Java 1 training",
	"sub-topics": [
		{
			"id": "1",
			"name": "Java collection",
			"speaker": "Alice"
		},
		{
			"id": "2",
			"name": "Multithreading",
			"speaker": "Bob"
		}
	]
}'
```

To update, use 

```
POST /events/_update/1
{
  "doc": {
    "title": "Java core"
  }
}
```

To create list of data, can use

```
POST /events/_create/2
{
	"list": [
		{
			"id": "1",
			"title": "Java 1",
			"eventType": "Training",
			"dateTime": "2022-01-01",
			"place": "conference room 1",
			"description": "Java 1 training",
			"sub-topics": [
				{
					"id": "1",
					"name": "Java collection",
					"speaker": "Alice"
				},
				{
					"id": "2",
					"name": "Multithreading",
					"speaker": "Bob"
				}
			]
		},
		{
			"id": "2",
			"title": "AWS 1",
			"eventType": "Workshop",
			"dateTime": "2022-01-02",
			"place": "Conference room 2",
			"description": "AWS 1 workshop",
			"sub-topics": [
				{
					"id": "3",
					"name": "SQS",
					"speaker": "Celine"
				},
				{
					"id": "4",
					"name": "VPC",
					"speaker": "Donald"
				},
				{
					"id": "5",
					"name": "IAM",
					"speaker": "Edgar"
				}
			]
		},
		{
			"id": "3",
			"title": "Java 2",
			"eventType": "Training",
			"dateTime": "2022-01-02",
			"place": "conference room 3",
			"description": "description 3",
			"sub-topics": [
				{
					"id": "6",
					"name": "Springboot",
					"speaker": "Celine"
				},
				{
					"id": "7",
					"name": "Spring data JPA",
					"speaker": "Donald"
				},
				{
					"id": "8",
					"name": "Spring cloud",
					"speaker": "Edgar"
				}
			]
		},		
		{
			"id": "4",
			"title": "Java 3",
			"eventType": "Training",
			"dateTime": "2022-01-03",
			"place": "conference room 3",
			"description": "description 4",
			"sub-topics": [
				{
					"id": "6",
					"name": "Springboot advance",
					"speaker": "Celine"
				},
				{
					"id": "7",
					"name": "Spring data JPA advance",
					"speaker": "Donald"
				},
				{
					"id": "8",
					"name": "Spring cloud advance",
					"speaker": "Edgar"
				}
			]
		}
	]
}
```

Reference: https://www.elastic.co/guide/en/elasticsearch/reference/current/array.html

To `get all events`, try

```
GET /events/_search
```

To `get events with title Java` try

```
GET /events/_search
{
  "query": {
    "match": {
      "list.title": "Java"
    }
  }
}
```

or

```
GET /events/_search
{
  "query": {
    "query_string": {
      "default_field": "list.title",
      "query": "Java"
    }
  }
}
```

To `get events with title Java and from 2022-01-02` try

```

```

Reference: https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-query-string-query.html
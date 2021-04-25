# warehouse

## Quick Setup and Run
`$ sbt compile`
`$ sbt "run --inventory-path /path/to/inventory.json --products-path /path/to/products.json"`

## Deploy jar to docker image
Loads `inventory.json` and `product.json` from `src/main/resources`.
Replace these files if you want to boot the app using a different inventory and products.

`$ sudo docker build -t warehouse .`

`$ sudo docker run warehouse`

## Deploy jar locally and run
Optional, clean up old builds:

`$ sbt clean`

Create fat jar:

`$ sbt assembly`

Run jar (assumes scala 2.11.12):

`scala target/scala-2.11/warehouse-assembly-0.1-SNAPSHOT.jar --inventory-path "/path/to/inventory.json" --products-path "/path/to/products.json"
`

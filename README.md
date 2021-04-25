# warehouse

## Quick Setup and Run
`$ sbt compile`

`$ sbt "run --inventory-path /path/to/inventory.json --products-path /path/to/products.json"`

## Deploy jar to docker image
Loads `inventory.json` and `product.json` from `src/main/resources`.
Replace these files if you want to boot the app using a different inventory and products.

TODO: Mount a folder that the docker container/warehouse app can load files from.

`$ sbt assembly`

`$ sudo docker build --no-cache -t warehouse .`

Run container and enable user input from stdio to our app:

`$ sudo docker run -v $(pwd):/src -it warehouse`

Debugging/testing command:

`sbt clean && sbt assembly && sudo docker build --no-cache -t warehouse .`

## Deploy jar locally and run
Optional, clean up old builds:

`$ sbt clean`

Create fat jar:

`$ sbt assembly`

Run jar (assumes scala 2.11.12):

`scala target/scala-2.11/warehouse-assembly-0.1-SNAPSHOT.jar --inventory-path "/path/to/inventory.json" --products-path "/path/to/products.json"
`

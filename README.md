# Build

```shell
mvn package
```

# Usage

## Java

```shell
java -jar view-points.jar <mesh file> <number of view spots>
```

## Serverless (local)
```shell
cat contrib/serverless/mesh.json | sls invoke local --function getViewPoints
```

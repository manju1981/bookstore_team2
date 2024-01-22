

## Create an server for the bootcamp:


```bash
'''docker build -t bootcamp/gocd-server - < Dockerfile
```

## Run the GOCD server

```bash
docker-compose -f docker-gocd-server.yml up
```

## Create an agent for the bootcamp:


```bash
docker build -t bootcamp/gocd-agent - < Dockerfile
```

## Run the GOCD  agent locally or connect to remote go cd server 

```bash
docker-compose -f docker-gocd-agent.yml up
```

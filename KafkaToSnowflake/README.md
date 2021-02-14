# Kafka to Snowflake Connectivity

## Introduction ->

Snowflake is a cloud-built analytic data warehouse. Snowflake delivers a data warehouse that is simpler, easy to access and much more versatile than conventional offerings of data warehouses

This blog talks about 

1. Create Kafka Topic from Conduktor UI.
2. Produce avro messages to kafka topic using Kafka Producer CLI.
3. Deploy Snowflake Kafka Connector on Kafka Connect machine.
4. Check whether messages are present in snowflake table.

We need to sign up for for Snowflake cloud data platform before going ahead. We can do that from this [link](https://www.snowflake.com/try-the-cloud-data-platform/) for any cloud(Azure, AWS or GCP). For this POC, wer are using Microsoft Azure cloud.
Once account is created, keep a note for the Snowflake public URL.



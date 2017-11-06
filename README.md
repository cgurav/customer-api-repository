# customer-api-repository
Repository to maintain customer api artifacts


Introduction
============

This document provides the details about the Customer Details API

Overview
--------

The Customer Details API is designed and implemented to provide the customer information over HTTP ReST. This API offers the secured access to perform CRUD operations on Customer Details.

To establish an end-to-end use case, the following components are provided

-   Consumer Apps - simulating the consumer access to Customer Details API
-   Customer Details API - the API for consumers
-   Customer Provider API - acting system of records

Details of Components
---------------------

The following are the details of components to demonstrate end-to-end use cases

Customer Details API:

This API is consumed by Consumer Apps replicating the data and user representatives using the mobile applications. This API exposes the following functionality over HTTP ReST

1.  Get list of Customers

2.  Create new Customer

3.  Update list of Customers - Bulk Update

4.  Update Customer

5.  Delete Customer

Customer Provider API:

This is a Mock for Customer Provider API. This application loads some preconfigured customer details in Object-Store as initial load when the application starts.

The in memory object store is used as Database, maintaining Customer records.

The API allows performing the corresponding CRUD operations on the data in Object-Store.

Consumer App:

This app simulates the consumer apps - invoking the Customer Details API operations. Below are the 2 simulations:

1.  Consumer App - syncing the Customer Data. Poller runs after configured interval (5 mins) to invoke the Customer Details API, fetches and logs the retrieved customers.

2.  Customer Representative App - Get a customer details and Bulk update customer details. - Mule flows (with HTTP inbound) when invoked call GET or PATCH of Customer Details API

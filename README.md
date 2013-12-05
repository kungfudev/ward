
Methods
=======

Find All
--------
http://ward.kungfudev.com/wards

Find By Id
----------
http://ward.kungfudev.com/wards/10204022

Find By Coordinate
------------------
http://ward.kungfudev.com/wards/search/findByCoordinates?longitude=18.837233&latitude=-33.958596

Find By Province Name
---------------------
http://ward.kungfudev.com/wards/search/findByProvinceName?provinceName=Western Cape

Find By Municipality Id
-----------------------
http://ward.kungfudev.com/wards/search/findByMunicipalityId?municipalityId=CPT

Ward JSON Example
=================

> {
>     "id": "10204022",
>     "number": 22,
>     "area": 9.0962029885,
>     "population": 3911,
>     "province": {
>         "name": "Western Cape"
>     },
>     "municipality": {
>         "id": "WC024",
>         "name": "Stellenbosch Local Municipality"
>     }
> }

Methods
=======

Find All Wards
--------------
http://ward.kungfudev.com/wards

Get Ward By Id
--------------
http://ward.kungfudev.com/wards/10204022

Find Wards By Coordinates
------------------------
http://ward.kungfudev.com/wards/search/findByCoordinates?longitude=18.837233&latitude=-33.958596

Find Wards By Province Name
---------------------------
http://ward.kungfudev.com/wards/search/findByProvinceName?provinceName=Western+Cape

Find Wards By Municipality Id
-----------------------------
http://ward.kungfudev.com/wards/search/findByMunicipalityId?municipalityId=CPT

Get Ward Geometry By Id
-----------------------
http://ward.kungfudev.com/wards/geometry/10204022

Find Wards Geometry By Coordinates
----------------------------------
http://ward.kungfudev.com/wards/geometry/search/findByCoordinates?longitude=18.837233&latitude=-33.958596

Find Wards Geometry Municipality Id
-----------------------------------
http://ward.kungfudev.com/wards/geometry/search/findByMunicipalityId?municipalityId=CPT

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
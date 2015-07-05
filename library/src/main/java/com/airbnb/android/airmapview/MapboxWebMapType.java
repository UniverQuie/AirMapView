package com.airbnb.android.airmapview;

import android.content.res.Resources;
import android.os.Bundle;

public class MapboxWebMapType extends AirMapType
{
    private String mapId;
    private String accessToken;

    protected static final String ARG_MAPBOX_ACCESS_TOKEN = "MAPBOX_ACCESS_TOKEN";
    protected static final String ARG_MAPBOX_MAPID = "MAPBOX_MAPID";

    /**
     * Primary Constructor
     * @param accessToken Mapbox Access Token
     * @param mapId Mapbox Map Id
     */
    public MapboxWebMapType(String accessToken, String mapId) {
        super("mapbox.html", "https://api.tiles.mapbox.com/mapbox.js/v2.2.1", "www.mapbox.com");
        this.accessToken = accessToken;
        this.mapId = mapId;
    }

    /**
     * Private Constructor used for Bundle Serialization
     * @param fileName File Name
     * @param mapUrl Map URL
     * @param domain Map Domain
     * @param accessToken Mapbox Access Token
     * @param mapId Mapbox Map Id
     */
    private MapboxWebMapType(String fileName, String mapUrl, String domain, String accessToken, String mapId) {
        super(fileName, mapUrl, domain);
        this.accessToken = accessToken;
        this.mapId = mapId;
    }

    public Bundle toBundle(Bundle bundle) {
        bundle.putString(ARG_MAP_DOMAIN, getDomain());
        bundle.putString(ARG_MAP_URL, getMapUrl());
        bundle.putString(ARG_FILE_NAME, getFileName());
        bundle.putString(ARG_MAPBOX_ACCESS_TOKEN, accessToken);
        bundle.putString(ARG_MAPBOX_MAPID, mapId);
        return bundle;
    }

    public static MapboxWebMapType fromBundle(Bundle bundle) {
        AirMapType airMapType = AirMapType.fromBundle(bundle);
        return new MapboxWebMapType(airMapType.getFileName(), airMapType.getMapUrl(), airMapType.getDomain(), bundle.getString(ARG_MAPBOX_ACCESS_TOKEN, ""), bundle.getString(ARG_MAPBOX_MAPID, ""));
    }

    @Override
    public String getMapData(Resources resources) {
        String mapData = super.getMapData(resources);

        mapData = mapData.replace(ARG_MAPBOX_ACCESS_TOKEN, accessToken);
        mapData = mapData.replace(ARG_MAPBOX_MAPID, mapId);

        return mapData;
    }
}


package com.faramarz.tictacdev.network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Asn {

    @SerializedName("asn")
    @Expose
    private String asn;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("route")
    @Expose
    private String route;
    @SerializedName("type")
    @Expose
    private String type;

    public String getAsn() {
        return asn;
    }

    public void setAsn(String asn) {
        this.asn = asn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

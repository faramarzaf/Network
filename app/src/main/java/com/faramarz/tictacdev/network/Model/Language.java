
package com.faramarz.tictacdev.network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("native")
    @Expose
    private String _native;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNative() {
        return _native;
    }

    public void setNative(String _native) {
        this._native = _native;
    }

}

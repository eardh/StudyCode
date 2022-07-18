package com.eardh.source;

import com.eardh.protocol.Serializer;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TestGson {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new Serializer.ClassCodec()).create();
        System.out.println(gson.toJson(String.class));
    }
}

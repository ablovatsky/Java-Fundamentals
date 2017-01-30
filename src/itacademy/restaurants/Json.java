package itacademy.restaurants;

import com.google.gson.Gson;

/**
 * Created by aVa on 29.01.2017.
 */
public class Json {
    private String json = "";

    private Json() {
    }

    private String getJson(){
        json += "}";
        return json;
    }

    public static Builder newBuilder(){
        return new Json().new Builder();
    }
    public class Builder<T> {
        private Builder() {
            Json.this.json += "{";
        }

        public Builder addObject(String name, T obj) {
            Gson gson = new Gson();
            String json = Json.this.json;
            json += jsonIsEmpty();
            json += "\""+ name +"\": ";
            json += gson.toJson(obj);
            Json.this.json = json;
            return this;
        }

        public Builder addValue(String name, T value) {
            Gson gson = new Gson();
            String json = Json.this.json;
            json += jsonIsEmpty();
            json += "\""+ name +"\": ";
            json += gson.toJson(value);
            Json.this.json = json;
            return this;
        }

        public Builder createObject(String name){
            String json = Json.this.json;
            json += jsonIsEmpty();
            json += "\""+ name +"\": {";
            Json.this.json = json;
            return this;
        }

        public Builder apply(){
            Json.this.json += "}";
            return this;
        }

        public String getJson() {
            return Json.this.getJson();
        }

        private String jsonIsEmpty() {
            String json = Json.this.json;
            if (json.length() > 1 && json.toCharArray()[json.length() - 1] != '{') {
                return ", ";
            }
            return "";
        }
    }
}

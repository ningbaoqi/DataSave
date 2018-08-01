### 使用JsonObject对Json进行解析

```
/**
 * 使用JSONObject对Json进行解析
 * @param data
 */
private void parseJsonWithJsonObject(String data) {
    try {
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String id = object.getString("id");
            String name = object.getString("name");
            String version = object.getString("version");
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }
}
```

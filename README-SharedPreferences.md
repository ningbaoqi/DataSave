### SharedPreferences使用简介

```
SharedPreferences sharedPreferences = getSharedPreferences(SHAREDPREFERENCENAME, MODE_PRIVATE);
//SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
SharedPreferences.Editor editor = sharedPreferences.edit();
editor.putString("key1", "value1");
editor.putBoolean("key2", true);
editor.putInt("key3", 1);
editor.commit();
editor.remove("key1");
editor.commit();
Toast.makeText(MainActivity.this, sharedPreferences.getBoolean("key2", false) + "", Toast.LENGTH_LONG).show();
```

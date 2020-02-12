package com.cs442.dsuraj.quantumc.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.AbstractCursor;
import android.os.Bundle;

public class JSONArrayCursor extends AbstractCursor {

    public static final String DATA_KEY = "d";

    private JSONArray mJsonArray;
    private String[] mColumnNames;
    private JSONObject mJsonObject;

    public JSONArrayCursor(JSONObject jsonObject) {
        if (jsonObject != null) {
            JSONObject tempObj = null;
            if(jsonObject.has(DATA_KEY)) {
                try {
                    mJsonArray = jsonObject.getJSONArray(DATA_KEY);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (mJsonArray == null) {
                try {
                    tempObj = jsonObject.getJSONObject(DATA_KEY);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (tempObj != null) {
                    mJsonArray = new JSONArray();
                    mJsonArray.put(tempObj);
                }
            }

            initialize();
        }
    }

    public JSONArrayCursor(JSONArray jsonArray) {
        mJsonArray = jsonArray;
        initialize();
    }

    public JSONArrayCursor() {
    }

    public String[] jsonArrayTocursor(JSONArray jsonArray){
        mJsonArray = jsonArray;
       return initialize();
    }

    private String[] initialize() {
        if (mJsonArray != null && mJsonArray.length() > 0) {
            JSONObject jsonObject = null;
            try {
                jsonObject = mJsonArray.getJSONObject(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jsonObject != null) {
                Iterator<String> keys = jsonObject.keys();
                if (keys != null) {
                    List<String> list = new ArrayList<String>();
                    while(keys.hasNext()) {
                        String key = keys.next();
                        list.add(key);
                    }
                    mColumnNames = new String[list.size()];
                    mColumnNames = list.toArray(mColumnNames);
                    return mColumnNames;
                }
            }
            return mColumnNames;
        }else {
            return mColumnNames;
        }
    }

    @Override
    public int getCount() {
        if (mJsonArray != null) {
            return mJsonArray.length();
        }
        return 0;
    }

    @Override
    public String[] getColumnNames() {
        if (mColumnNames != null) {
            return mColumnNames;
        }
        return new String[] {};
    }

    @Override
    public String getString(int columnIndex) {
        if (mJsonObject != null) {
            String name = getColumnName(columnIndex);
            if (name != null) {
                try {
                    return mJsonObject.getString(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public short getShort(int columnIndex) {
        if (mJsonObject != null) {
            String name = getColumnName(columnIndex);
            if (name != null) {
                try {
                    return (short)mJsonObject.getInt(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    @Override
    public int getInt(int columnIndex) {
        if (mJsonObject != null) {
            String name = getColumnName(columnIndex);
            if (name != null) {
                try {
                    return mJsonObject.getInt(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    @Override
    public long getLong(int columnIndex) {
        if (mJsonObject != null) {
            String name = getColumnName(columnIndex);
            if (name != null) {
                try {
                    return mJsonObject.getLong(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    @Override
    public float getFloat(int columnIndex) {
        if (mJsonObject != null) {
            String name = getColumnName(columnIndex);
            if (name != null) {
                try {
                    return (float)mJsonObject.getDouble(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    @Override
    public double getDouble(int columnIndex) {
        if (mJsonObject != null) {
            String name = getColumnName(columnIndex);
            if (name != null) {
                try {
                    return mJsonObject.getDouble(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    @Override
    public boolean isNull(int columnIndex) {
        if (mJsonObject != null) {
            String name = getColumnName(columnIndex);
            if (name != null) {
                return mJsonObject.isNull(name);
            }
        }
        return true;
    }

    @Override
    public boolean onMove(int oldPosition, int newPosition) {
        if (mJsonArray == null) return false;
        if (oldPosition != newPosition || mJsonObject == null) {

            try {
                mJsonObject = mJsonArray.getJSONObject(newPosition);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return true;
        }
        return false;
    }

    @Override
    public Bundle respond(Bundle extras) {
        Bundle result = new Bundle();
        result.putString(DATA_KEY, toJSONString());
        result.putInt("count", getCount());
        result.putStringArray("columns", mColumnNames);
        result.putInt("position", mPos);
        result.putString("current", (mJsonObject != null) ? mJsonObject.toString() : "");

        return result;
    }

    @Override
    public String toString() {
        if (mJsonArray != null) {
            return toJSONString();
        }
        return super.toString();
    }

    public String toJSONString() {
        if (mJsonArray != null) {
            JSONObject result = new JSONObject();
            try {
                result.put(DATA_KEY, mJsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result.toString();
        }
        return "";
    }
}
package com.example.studytoolsTemp.network;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.studytoolsTemp.R;
import com.example.studytoolsTemp.activity.LoginActivity;
import com.example.studytoolsTemp.activity.student.StudentProfileActivity;
import com.example.studytoolsTemp.activity.teacher.TeacherProfileActivity;
import com.example.studytoolsTemp.adapters.ExamListAdapter;
import com.example.studytoolsTemp.adapters.ResultListAdapter;
import com.example.studytoolsTemp.adapters.FileListAdapter;
import com.example.studytoolsTemp.data.preference.AppPreference;
import com.example.studytoolsTemp.interfaces.CallBack;
import com.example.studytoolsTemp.models.Answer;
import com.example.studytoolsTemp.models.Course;
import com.example.studytoolsTemp.models.Exam;
import com.example.studytoolsTemp.models.ExamResult;
import com.example.studytoolsTemp.models.FileInfo;
import com.example.studytoolsTemp.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.studytoolsTemp.data.constant.AppConstant.BASE_URL;

public class DataHandler {

    static final String LOG_TAG = DataHandler.class.getSimpleName();
    private static ArrayList<FileInfo> fileList = new ArrayList<>();
    private static ArrayList<Exam> examList = new ArrayList<>();
    private static ArrayList<Course> courseList = new ArrayList<>();
    private static ArrayList<Answer> answerList = new ArrayList<>();
    private static ArrayList<ExamResult> examResults = new ArrayList<>();

    private static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void login(final Context context, final String user, final String pass) {

        String url = BASE_URL + "signin.php?username=" + user + "&password=" + pass;


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject userObject = jsonArray.getJSONObject(0);

                    if (!userObject.getString("error").equalsIgnoreCase("no")) {

                        showToast(context, "Wrong Username or Password");
                    } else {
                        String id = userObject.getString("id");
                        String name = userObject.getString("name");
                        String email = userObject.getString("email");
                        String mobile = userObject.getString("mobile");
                        String password = userObject.getString("password");
                        String username = userObject.getString("username");
                        String type = userObject.getString("type");

                        AppPreference.setData(context, new User(id, name, email, mobile, username, password));

                        if (type.equalsIgnoreCase("1")) {
                            Intent intent = new Intent(context, StudentProfileActivity.class);
                            context.startActivity(intent);
                        } else {
                            Intent intent = new Intent(context, TeacherProfileActivity.class);
                            context.startActivity(intent);
                        }

                    }

                } catch (JSONException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "" + error);
            }
        });

        requestQueue.add(stringRequest);

    }

    public static void signup(final Context context, final User user) {

        String url = BASE_URL + "signup.php";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(LOG_TAG, "" + error);

                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<>();

                stringMap.put("name", user.getName());
                stringMap.put("email", user.getEmail());
                stringMap.put("mobile", user.getMobile());
                stringMap.put("username", user.getUsername());
                stringMap.put("password", user.getPassword());

                return stringMap;
            }
        };

        requestQueue.add(stringRequest);

    }

    public static void update(final Context context, final User user) {

        String url = BASE_URL + "update.php";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();

                AppPreference.setData(context, user);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(LOG_TAG, "" + error);

                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<>();

                stringMap.put("id", AppPreference.getUserId(context));
                stringMap.put("name", user.getName());
                stringMap.put("email", user.getEmail());
                stringMap.put("mobile", user.getMobile());
                stringMap.put("username", user.getUsername());
                stringMap.put("password", user.getPassword());

                return stringMap;
            }
        };

        requestQueue.add(stringRequest);


    }

    public static void getFilesOfUser(final Context context, String userid, boolean isQuestion) {

        String url = BASE_URL + "filelist.php";

        if (userid != null && isQuestion) {
            url = url + "?userid=" + userid + "&filetype=" + 2;
        } else if (userid != null) {
            url = url + "?userid=" + userid + "&filetype=" + 1;
        } else if (userid == null && isQuestion) {
            url = url + "?filetype=" + 2;
        } else {
            url = url + "?filetype=" + 1;
        }


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    JSONObject flagObject = jsonArray.getJSONObject(0);

                    if (flagObject.getString("success").equalsIgnoreCase("true")) {

                        fileList.clear();

                        for (int i = 1; i < jsonArray.length(); i++) {
                            JSONObject fileObject = jsonArray.getJSONObject(i);

                            fileList.add(new FileInfo(fileObject.getInt("id"),
                                    fileObject.getString("username"),
                                    fileObject.getString("filename"),
                                    fileObject.getString("filedescription")
                            ));
                        }

                        RecyclerView recyclerViewList = ((Activity) context).findViewById(R.id.recyclerView_fileList);
                        FileListAdapter fileListAdapter = new FileListAdapter(context, fileList);

                        recyclerViewList.setAdapter(fileListAdapter);
                        recyclerViewList.setLayoutManager(new LinearLayoutManager(context));


                    } else {

                        showToast(context, "No Data Found For You");
                    }

                } catch (JSONException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "" + error);
            }
        });

        requestQueue.add(stringRequest);

    }

    public static void getQuestionsOfUser(final Context context, String userid, final CallBack<FileInfo> filesCallBack) {

        String url = BASE_URL + "filelist.php";

        url = url + "?userid=" + userid + "&filetype=" + 2;

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    JSONObject flagObject = jsonArray.getJSONObject(0);

                    if (flagObject.getString("success").equalsIgnoreCase("true")) {

                        fileList.clear();

                        for (int i = 1; i < jsonArray.length(); i++) {
                            JSONObject fileObject = jsonArray.getJSONObject(i);

                            fileList.add(new FileInfo(fileObject.getInt("id"),
                                    fileObject.getString("username"),
                                    fileObject.getString("filename"),
                                    fileObject.getString("filedescription"),
                                    fileObject.getInt("questions")
                            ));
                        }

                        filesCallBack.onSuccess(fileList);

                    } else {

                        showToast(context, "No Data Found For You");
                    }

                } catch (JSONException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "" + error);
            }
        });

        requestQueue.add(stringRequest);

    }

    public static void getExamList(final Context context, final CallBack<Exam> onExamCallBack, final int userId, final int semester) {


        String url = BASE_URL + "exampdflist.php";


        if (userId != -1 && semester != -1) {
            url += "?userid=" + userId + "&semester=" + semester;
        } else if (userId != -1)
            url += "?userid=" + userId;

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    JSONObject flagObject = jsonArray.getJSONObject(0);

                    examList.clear();

                    if (flagObject.getString("success").equalsIgnoreCase("true")) {


                        int length = jsonArray.length();

                        for (int i = 1; i < length; i++) {
                            JSONObject examObject = jsonArray.getJSONObject(i);

                            examList.add(new Exam(examObject.getInt("id"),
                                    examObject.getString("examtitle"),
                                    examObject.getString("filename"),
                                    Integer.parseInt(examObject.getString("duration"))
                            ));
                        }

                    } else {

                        showToast(context, "No Data Found For You");
                    }

                    if (userId != -1) {
                        RecyclerView recyclerViewList = ((Activity) context).findViewById(R.id.recyclerView_examList);
                        ExamListAdapter examListAdapter = new ExamListAdapter(context, examList);

                        recyclerViewList.setAdapter(examListAdapter);
                        recyclerViewList.setLayoutManager(new LinearLayoutManager(context));
                    }

                    onExamCallBack.onSuccess(examList);

                } catch (JSONException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "" + error);
            }
        });

        requestQueue.add(stringRequest);

    }

    public static void getCourseList(final Context context, final CallBack<Course> onCourseCallBack, final int semester) {


        String url = BASE_URL + "courselist.php";

        url += "?semester=" + semester;


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    JSONObject flagObject = jsonArray.getJSONObject(0);

                    if (flagObject.getString("success").equalsIgnoreCase("true")) {

                        courseList.clear();

                        int length = jsonArray.length();

                        for (int i = 1; i < length; i++) {
                            JSONObject courseObject = jsonArray.getJSONObject(i);

                            courseList.add(new Course(courseObject.getInt("id"),
                                    Integer.parseInt(courseObject.getString("semester")),
                                    courseObject.getString("subject")
                            ));
                        }

                        onCourseCallBack.onSuccess(courseList);

                    } else {

                        showToast(context, "No Data Found For You");
                    }

                } catch (JSONException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "" + error);
            }
        });

        requestQueue.add(stringRequest);

    }

    public static void getResultList(final Context context, final int userId, final int examid) {


        String url = BASE_URL + "examresult.php";

        if (userId != -1)
            url += "?userid=" + userId;
        else if (examid != -1)
            url += "?examid=" + examid;

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    JSONObject flagObject = jsonArray.getJSONObject(0);

                    examResults.clear();

                    if (flagObject.getString("success").equalsIgnoreCase("true")) {


                        int length = jsonArray.length();

                        for (int i = 1; i < length; i++) {
                            JSONObject examObject = jsonArray.getJSONObject(i);

                            examResults.add(new ExamResult(examObject.getString("name"),
                                    examObject.getString("examtitle"),
                                    examObject.getInt("result")));
                        }

                        // onExamCallBack.onSuccess(examResults);

                    } else {

                        showToast(context, "No Data Found For You");
                    }

                    RecyclerView recyclerViewList = ((Activity) context).findViewById(R.id.recyclerView_examResultList);
                    ResultListAdapter examListAdapter = new ResultListAdapter(context, examResults);

                    recyclerViewList.setAdapter(examListAdapter);
                    recyclerViewList.setLayoutManager(new LinearLayoutManager(context));

                } catch (JSONException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "" + error);
            }
        });

        requestQueue.add(stringRequest);

    }

    public static void getAnswerList(final Context context, final CallBack<Answer> answerCallBack, final int examId) {


        String url = BASE_URL + "answerlist.php" + "?examid=" + examId;

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    JSONObject flagObject = jsonArray.getJSONObject(0);

                    if (flagObject.getString("success").equalsIgnoreCase("true")) {

                        answerList.clear();

                        int length = jsonArray.length();

                        for (int i = 1; i < length; i++) {
                            JSONObject questionObject = jsonArray.getJSONObject(i);

                            answerList.add(new Answer(questionObject.getInt("examid"),
                                    questionObject.getInt("questionnum"),
                                    questionObject.getInt("answer")
                            ));
                        }

                        answerCallBack.onSuccess(answerList);

                    } else {

                        showToast(context, "No Data Found For You");
                    }

                } catch (JSONException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "" + error);
            }
        });

        requestQueue.add(stringRequest);

    }

    public static void storeResult(final Context context, final int userid, final int examid, final int result) {

        String url = BASE_URL + "storeresult.php";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(LOG_TAG, "" + error);

                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<>();

                stringMap.put("userid", String.valueOf(userid));
                stringMap.put("examid", String.valueOf(examid));
                stringMap.put("result", String.valueOf(result));
                return stringMap;
            }
        };

        requestQueue.add(stringRequest);

    }

    public static void storeCourse(final Context context, final int semesterId, final String courseTitle) {

        String url = BASE_URL + "storecourse.php";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(LOG_TAG, "" + error);

                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<>();

                stringMap.put("semester", String.valueOf(semesterId));
                stringMap.put("coursetitle", courseTitle);
                return stringMap;
            }
        };

        requestQueue.add(stringRequest);

    }

    public static void storeAnswer(final Context context, final int examid, final int questionnum, final int answer) {

        String url = BASE_URL + "storeanswer.php";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(LOG_TAG, "" + error);

                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<>();

                stringMap.put("examid", String.valueOf(examid));
                stringMap.put("questionnum", String.valueOf(questionnum));
                stringMap.put("answer", String.valueOf(answer));
                return stringMap;
            }
        };

        requestQueue.add(stringRequest);

    }

    public static void logout(final Context context) {

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        alertDialog.setTitle("Logging Out");
        alertDialog.setMessage("Are you sure ? ");
        alertDialog.setCancelable(false);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AppPreference.clearData(context);

                context.startActivity(new Intent(context, LoginActivity.class));

                Toast.makeText(context, "Logged Out Successfully", Toast.LENGTH_SHORT).show();

            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }


}

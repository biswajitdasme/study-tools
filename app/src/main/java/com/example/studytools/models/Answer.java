package com.example.studytools.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {
    private int examId;
    private int questionNum;
    private int answer;

    public Answer() {
    }

    public Answer(int examId, int questionNum, int answer) {
        this.examId = examId;
        this.questionNum = questionNum;
        this.answer = answer;
    }

    protected Answer(Parcel in) {
        examId = in.readInt();
        questionNum = in.readInt();
        answer = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(examId);
        dest.writeInt(questionNum);
        dest.writeInt(answer);
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}

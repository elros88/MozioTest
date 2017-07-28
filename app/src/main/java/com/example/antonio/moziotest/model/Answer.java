package com.example.antonio.moziotest.model;

import com.example.antonio.moziotest.utils.Constants;

/**
 * Created by antonio on 7/27/17.
 */

public class Answer
{
    private String migrainesAnswers;
    private Integer age;
    private String gender;
    private String substanceAnswer;
    private Integer probability;

    public Answer()
    {
        migrainesAnswers = "";
        age = -1;
        gender = "";
        substanceAnswer = "";
        probability = 0;
    }

    public Answer(String migranesAnswers,
                  Integer age,
                  String gender,
                  String substanceAnswer,
                  Integer probability)
    {
        this.migrainesAnswers = migranesAnswers;
        this.age = age;
        this.gender = gender;
        this.substanceAnswer = substanceAnswer;
        this.probability = probability;
    }

    public String getMigrainesAnswers() {
        return migrainesAnswers;
    }

    public void setMigrainesAnswers(String migrainesAnswers) {
        this.migrainesAnswers = migrainesAnswers;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSubstanceAnswer() {
        return substanceAnswer;
    }

    public void setSubstanceAnswer(String substanceAnswer) {
        this.substanceAnswer = substanceAnswer;
    }

    public String calculateProbability()
    {
        if(migrainesAnswers.equals(Constants.affirmativeAnswer))
            probability += 25;

        if(age <= 15)
            probability += 25;

        if(gender.equals(Constants.maleAnswer))
            probability += 25;

        if(substanceAnswer.equals(Constants.affirmativeAnswer))
            probability += 25;

        return probability.toString() + "%";
    }

    public boolean checkAnswers()
    {
        if(migrainesAnswers.equals(""))
            return false;
        if(age < 1)
            return false;
        if(gender.equals(""))
            return false;
        if(substanceAnswer.equals(""))
            return false;

        return true;
    }
}

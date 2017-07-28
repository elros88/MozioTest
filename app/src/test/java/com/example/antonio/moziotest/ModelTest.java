package com.example.antonio.moziotest;

import com.example.antonio.moziotest.model.Answer;
import com.example.antonio.moziotest.utils.Constants;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by antonio on 7/27/17.
 */

public class ModelTest
{
    @Test
    public void femaleProbabilityTest()
    {
        Answer answer = new Answer();

        answer.setGender(Constants.femaleAnswer);
        answer.setSubstanceAnswer(Constants.negativeAnswer);
        answer.setMigrainesAnswers(Constants.negativeAnswer);
        answer.setAge(25);

        Assert.assertEquals(answer.calculateProbability(), "0%");
    }

    @Test
    public void maleProbabilityTest()
    {
        Answer answer = new Answer();

        answer.setGender(Constants.maleAnswer);
        answer.setSubstanceAnswer(Constants.negativeAnswer);
        answer.setMigrainesAnswers(Constants.negativeAnswer);
        answer.setAge(25);

        Assert.assertEquals(answer.calculateProbability(), "25%");
    }

    @Test
    public void migraineNegativeProbabilityTest()
    {
        Answer answer = new Answer();

        answer.setGender(Constants.femaleAnswer);
        answer.setSubstanceAnswer(Constants.negativeAnswer);
        answer.setMigrainesAnswers(Constants.negativeAnswer);
        answer.setAge(25);

        Assert.assertEquals(answer.calculateProbability(), "0%");
    }

    @Test
    public void migraineAffirmativeProbabilityTest()
    {
        Answer answer = new Answer();

        answer.setGender(Constants.femaleAnswer);
        answer.setSubstanceAnswer(Constants.negativeAnswer);
        answer.setMigrainesAnswers(Constants.affirmativeAnswer);
        answer.setAge(25);

        Assert.assertEquals(answer.calculateProbability(), "25%");
    }

    @Test
    public void substanceNegativeProbabilityTest()
    {
        Answer answer = new Answer();

        answer.setGender(Constants.femaleAnswer);
        answer.setSubstanceAnswer(Constants.negativeAnswer);
        answer.setMigrainesAnswers(Constants.negativeAnswer);
        answer.setAge(25);

        Assert.assertEquals(answer.calculateProbability(), "0%");
    }

    @Test
    public void substanceAffirmativeProbabilityTest()
    {
        Answer answer = new Answer();

        answer.setGender(Constants.femaleAnswer);
        answer.setSubstanceAnswer(Constants.affirmativeAnswer);
        answer.setMigrainesAnswers(Constants.negativeAnswer);
        answer.setAge(25);

        Assert.assertEquals(answer.calculateProbability(), "25%");
    }

    @Test
    public void ageNegativeProbabilityTest()
    {
        Answer answer = new Answer();

        answer.setGender(Constants.femaleAnswer);
        answer.setSubstanceAnswer(Constants.negativeAnswer);
        answer.setMigrainesAnswers(Constants.negativeAnswer);
        answer.setAge(25);

        Assert.assertEquals(answer.calculateProbability(), "0%");
    }

    @Test
    public void ageAffirmativeProbabilityTest()
    {
        Answer answer = new Answer();

        answer.setGender(Constants.femaleAnswer);
        answer.setSubstanceAnswer(Constants.negativeAnswer);
        answer.setMigrainesAnswers(Constants.negativeAnswer);
        answer.setAge(15);

        Assert.assertEquals(answer.calculateProbability(), "25%");
    }

    @Test
    public void fullProbabilityTest()
    {
        Answer answer = new Answer();

        answer.setGender(Constants.maleAnswer);
        answer.setSubstanceAnswer(Constants.affirmativeAnswer);
        answer.setMigrainesAnswers(Constants.affirmativeAnswer);
        answer.setAge(14);

        Assert.assertEquals(answer.calculateProbability(), "100%");
    }

    @Test
    public void ramdom75ProbabilityTest()
    {
        Answer answer = new Answer();

        answer.setGender(Constants.maleAnswer);
        answer.setSubstanceAnswer(Constants.negativeAnswer);
        answer.setMigrainesAnswers(Constants.affirmativeAnswer);
        answer.setAge(14);

        Assert.assertEquals(answer.calculateProbability(), "75%");
    }

    @Test
    public void ramdom50ProbabilityTest()
    {
        Answer answer = new Answer();

        answer.setGender(Constants.femaleAnswer);
        answer.setSubstanceAnswer(Constants.affirmativeAnswer);
        answer.setMigrainesAnswers(Constants.negativeAnswer);
        answer.setAge(14);

        Assert.assertEquals(answer.calculateProbability(), "50%");
    }
}

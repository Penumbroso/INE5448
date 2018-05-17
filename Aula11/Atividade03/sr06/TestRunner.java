package br.ufsc.cucumber.sr06;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "br.ufsc.cucumber.sr06.steps", features = "src/test/java/br/ufsc/cucumber/sr06/features", monochrome = true)
public class TestRunner {

}

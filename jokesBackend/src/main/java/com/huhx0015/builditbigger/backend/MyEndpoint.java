/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.huhx0015.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.huhx0015.jokeslib.JokeProducer;

import javax.inject.Named;

/** -----------------------------------------------------------------------------------------------
 *  [MyEndpoint] CLASS
 *  DESCRIPTION: An endpoint class we are exposing.
 *  -----------------------------------------------------------------------------------------------
 */

@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.builditbigger.huhx0015.com",
    ownerName = "backend.builditbigger.huhx0015.com",
    packagePath=""
  )
)

public class MyEndpoint {

    // sayHi():  A simple endpoint method that takes a name and says Hi back.
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);
        return response;
    }

    // fetchJoke(): An endpoint method that fetches a joke from the jokesLib library.
    @ApiMethod(name = "fetchJoke")
    public MyBean fetchJoke() {
        JokeProducer jokeProducer = new JokeProducer();
        MyBean response = new MyBean();
        response.setData(jokeProducer.tellMeAJoke());
        return response;
    }
}
package com.huhx0015.builditbigger.backend;

/** -----------------------------------------------------------------------------------------------
 *  [MyBean] CLASS
 *  DESCRIPTION: The object model for the data we are sending through endpoints.
 *  -----------------------------------------------------------------------------------------------
 */

public class MyBean {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    private String myData;

    /** GET METHODS ____________________________________________________________________________ **/

    public String getData() {
        return myData;
    }

    /** SET METHODS ____________________________________________________________________________ **/

    public void setData(String data) {
        myData = data;
    }
}
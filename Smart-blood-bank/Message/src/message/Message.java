/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.io.Serializable;

/**
 *
 * @author asus
 */
public class Message implements Serializable {
    public String message;
    public Message(String message)
    {
        this.message=message;
    }
    
    public String toString()
    {
        return message;
    }

}

package mrdm.crawler.bean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Wping on 2017/10/1.
 */
public class Message {
   private byte[] requestInfo = null;
   private byte[] heartInfo = null;
   private String content;

  public Message(String content){
       requestInfo = new byte[]{0x00,0x06,0x00,0x02,0x00,(byte)content.length()};
       this.content = content;

   }

   public Message(){
       heartInfo = new byte[]{0x00,0x06,0x00,0x00};
   }

   private ByteArrayOutputStream baos;

   public byte[] getBytes() throws IOException {
       if(baos==null)baos = new ByteArrayOutputStream();
       baos.reset();

       if(requestInfo!=null) {
           baos.write(requestInfo);
           baos.write(content.getBytes());
       }
       if(heartInfo!=null)
       baos.write(heartInfo);

       return baos.toByteArray();
   }
}

package rest;

import javax.ws.rs.*;

@Path( "/benutzer" )
public class WebService
{
   @GET @Produces( "text/plain" )
   public String halloText( @QueryParam("name") String name )
   {
      return "Hallo " + name;
   }

   @GET @Produces( "text/html" )
   public String halloHtml( @QueryParam("name") String name )
   {
      return "<html><title>HelloWorld</title><body><h2>Html: Hallo " + name + "</h2></body></html>";
   }
}
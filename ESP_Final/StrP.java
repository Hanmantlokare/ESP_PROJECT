import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class StrP implements Runnable {

    /***
     @Parameter - None.
     @Task
      * This is the main thread whcih binds the Server Scocket with specific port
      * and waits for the client requests.
     ***/
    Socket HttpConnection;

    public StrP(Socket HttpConnection) {
        this.HttpConnection = HttpConnection;
        new Thread(this).start();
    }

    public static void main(String[] args) throws Exception {

        ServerSocket MyServer = new ServerSocket(8080);

        try {
            /*
            Creates the ServerSocket and binds it to port 8080
            */
            while (true) {

                Socket C = MyServer.accept();
                new StrP(C);
                System.out.println("Connection Accepted from->" + C.getInetAddress().getHostAddress() + "\n");

            }
            /*
            Using InetAdress class's gethostAddress() to display IP of client which is being Served.
            */

            /*
            Obtain the Input and Output Streams from the TCP connection Socket
            */
////            DataInputStream Input_From_Client = new DataInputStream(new BufferedInputStream(C.getInputStream()));
////            DataOutputStream Output_To_Client = new DataOutputStream(new BufferedOutputStream(C.getOutputStream()));
////            DataInputStream Input_From_File;
////            File upload;
////            int a = 0;
////            String Http_Message = "";
////            /*
////            Read and Display HTTP request message from client
////            */
////            Http_Message = Input_From_Client.readUTF();
////            System.out.println(Http_Message);
////            /*
////            Process the entire client requst String to get the actual name of the requested File.
////            */
////            String[] Parse = Http_Message.split("\n\r");
////            String File_Name = Parse[0].substring(5, Parse[0].indexOf("HTTP/1.1") - 1);
////            byte[] upload_bytes;
////
////            /*
////            Obtain an instance of File object to represent requested resource
////            */
////            upload = new File(File_Name);
////            if (!upload.exists()) {
////             /*
////            If the File Doesn't exist propogate the appropriate response
////            */
////
////                Output_To_Client.writeUTF("404 NOT FOund");
////                Output_To_Client.flush();
////                System.out.println("The file requested by the client is not present");
////            } else {
////
////            /*
////            Send a HTTP reponse to denote the successful found of file and as a indicator that
////            what follows ahed is the byte data from the file.
////            If the file is present then Open a local stream on bytes and read all the bytes in a byte buffer
////            Then write the data from the byte buffer on the o/p Stream of Socket.
////            */
////                System.out.println("File requested -> " + File_Name);
////                Output_To_Client.writeUTF("HTTP/1.1 200 OK");
////                Output_To_Client.flush();
////            /*
////            Indicate the length of the file to client so that, Client can reserve adequate memory
////            To Store the file.
////            */
////                Output_To_Client.writeLong(upload.length());
////                upload_bytes = new byte[(int) upload.length()];
////                Output_To_Client.flush();
////                Input_From_File = new DataInputStream(new FileInputStream(new File(File_Name)));
////                Input_From_File.readFully(upload_bytes);
////            /*
////            Write the actual file bytes in the o/p Stream and then flush the
////            Stream again to ensure nothing is remaining in the Pipe.
////            Indicate on the Server that File uploading is finished Successfully.
////            */
////                Output_To_Client.write(upload_bytes);
////                Output_To_Client.flush();
////                System.out.println("File Uploaded Successfuly");
////
////            }


        } catch (SocketException s) {
            s.printStackTrace();
            System.out.println("Server Exiting...");
        } catch (IOException i) {

            i.printStackTrace();
        }


    }

    public void run() {
        try {


            DataOutputStream To_Browser = new DataOutputStream((HttpConnection.getOutputStream()));
            DataInputStream br = new DataInputStream(HttpConnection.getInputStream());


            byte[] request = new byte[1024];
            Thread.sleep(100);


            br.read(request);
            String req = new String(request);
            System.out.println(req);
            this.RequestHandler(req, To_Browser);
            To_Browser.close();
            br.close();
            HttpConnection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void RequestHandler(String s, DataOutputStream To_Client) {
        String append = "\r\n";
        String Content_disposition = "";
        String Status_Ok = "HTTP/1.0 200 OK" + append;
        String Status_Bad = "HTTP/1.0 404 Not Found" + append;
        String Content_Type_Index = "Content-type: " + "text/html" + append;
        StringTokenizer parser = new StringTokenizer(s);
        parser.nextToken();

        try {
            String resource = parser.nextToken();
            if (resource.equals("/")) {
                Content_Type_Index = "Content-type: " + "text/html" + append;
                To_Client.writeBytes(Status_Ok);
                To_Client.writeBytes(Content_Type_Index);
                To_Client.writeBytes(append);

                FileInputStream InFile = new FileInputStream("./index.html");
                byte[] buffer = new byte[1024];
                int current_length = 0;
                while ((current_length = InFile.read(buffer)) != -1) {
                    To_Client.write(buffer, 0, current_length);

                }
                To_Client.flush();
                InFile.close();

            }


            if (Pattern.matches(".*mp3.*", resource)) {

                if(resource.endsWith("?")) {
                    resource = resource.substring(0, resource.length() - 1);
                }
                System.out.println(resource);
                resource = "." + resource;

                Content_Type_Index = "Content-type: " + resource + "application/octet-state" + append;
                To_Client.writeBytes(Status_Ok);
                To_Client.writeBytes(Content_Type_Index);
                To_Client.writeBytes(append);


                FileInputStream InFile = new FileInputStream(resource);
                byte[] buffer = new byte[1024];
                int current_length = 0;
                while ((current_length = InFile.read(buffer)) != -1) {
                    To_Client.write(buffer, 0, current_length);

                }

                To_Client.flush();
                InFile.close();

            }


            if (Pattern.matches(".*mkv.*", resource)) {

                if(resource.endsWith("?")) {
                    resource = resource.substring(0, resource.length() - 1);
                }
                resource = "." + resource;

                Content_Type_Index = "Content-type: " + resource + "application/octet-state" + append;
                To_Client.writeBytes(Status_Ok);
                To_Client.writeBytes(Content_Type_Index);
                To_Client.writeBytes(append);


                FileInputStream InFile = new FileInputStream(resource);
                byte[] buffer = new byte[1024];
                int current_length = 0;
                while ((current_length = InFile.read(buffer)) != -1) {
                    To_Client.write(buffer, 0, current_length);

                }

                To_Client.flush();
                InFile.close();

            }


            if (Pattern.matches(".*jpg.*", resource)) {
                if(resource.endsWith("?")){
                    resource = resource.substring(0, resource.length() - 1);
                }
                resource = "." + resource;

                Content_Type_Index = "Content-type: " + resource + "image/jpeg" + append;
                To_Client.writeBytes(Status_Ok);
                To_Client.writeBytes(Content_Type_Index);
                To_Client.writeBytes(append);
                FileInputStream InFile = new FileInputStream(resource);
                byte[] buffer = new byte[1024];
                int current_length = 0;
                while ((current_length = InFile.read(buffer)) != -1) {
                    To_Client.write(buffer, 0, current_length);

                }

                To_Client.flush();
                InFile.close();

            }


            if (Pattern.matches(".*ico.*", resource)) {

                if(resource.endsWith("?")) {
                    resource = resource.substring(0, resource.length() - 1);
                }
                resource = "." + resource;

                Content_Type_Index = "Content-type: " + resource + "image/x-icon" + append;
                To_Client.writeBytes(Status_Ok);
                To_Client.writeBytes(Content_Type_Index);
                To_Client.writeBytes(append);
                FileInputStream InFile = new FileInputStream(resource);
                byte[] buffer = new byte[10];
                int current_length = 0;
                while ((current_length = InFile.read(buffer)) != -1) {
                    To_Client.write(buffer, 0, current_length);

                }

                To_Client.flush();
                InFile.close();

            }

        } catch (FileNotFoundException nf) {
            System.out.println("File not found");
        } catch (IOException nf) {
            nf.printStackTrace();
        }

    }


}

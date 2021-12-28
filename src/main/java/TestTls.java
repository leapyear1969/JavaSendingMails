import java.io.IOException;
import java.util.Arrays;
import javax.net.ssl.*;
import javax.net.ssl.SSLSocket;



public class TestTls {
    public static void main(String[] args) {

        SSLSocket sslSocket = new SSLSocket() {
            @Override
            public String[] getSupportedCipherSuites() {
                return new String[0];
            }

            @Override
            public String[] getEnabledCipherSuites() {
                return new String[0];
            }

            @Override
            public void setEnabledCipherSuites(String[] strings) {

            }

            @Override
            public String[] getSupportedProtocols() {
                return new String[0];
            }

            @Override
            public String[] getEnabledProtocols() {
                return new String[0];
            }

            @Override
            public void setEnabledProtocols(String[] strings) {

            }

            @Override
            public SSLSession getSession() {
                return null;
            }

            @Override
            public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {

            }

            @Override
            public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {

            }

            @Override
            public void startHandshake() throws IOException {

            }

            @Override
            public void setUseClientMode(boolean b) {

            }

            @Override
            public boolean getUseClientMode() {
                return false;
            }

            @Override
            public void setNeedClientAuth(boolean b) {

            }

            @Override
            public boolean getNeedClientAuth() {
                return false;
            }

            @Override
            public void setWantClientAuth(boolean b) {

            }

            @Override
            public boolean getWantClientAuth() {
                return false;
            }

            @Override
            public void setEnableSessionCreation(boolean b) {

            }

            @Override
            public boolean getEnableSessionCreation() {
                return false;
            }
        };
        System.out.println(Arrays.toString(sslSocket.getEnabledProtocols()));
    }
}









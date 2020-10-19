//package com.njust.hsicloud.core.assist.runc;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//
//import org.apache.sshd.client.SshClient;
//import org.apache.sshd.client.channel.ChannelShell;
//import org.apache.sshd.client.channel.ClientChannel;
//import org.apache.sshd.client.channel.ClientChannelEvent;
//import org.apache.sshd.client.future.ConnectFuture;
//import org.apache.sshd.client.session.ClientSession;
//import org.apache.sshd.common.channel.PtyMode;
//import org.apache.sshd.common.util.io.NoCloseInputStream;
//import org.apache.sshd.common.util.io.NoCloseOutputStream;
//
//
//public class SshClientTest {
//    public static void main(String[] args) throws Exception {
//        try (SshClient client = SshClient.setUpDefaultClient()) {
//            //PropertyResolverUtils.updateProperty(client, FactoryManager.IDLE_TIMEOUT, 30000L);
//            client.start();
//            ConnectFuture connect = client.connect("username", "xxx.xxx.xx.x", 22);
//            if (connect.await(5000L)) {
//                try (ClientSession session = connect.getSession()) {
//                    session.addPasswordIdentity("password");
//                    session.auth().verify(5000L);
//                    try (ChannelShell channel = (ChannelShell) session.createChannel(ClientChannel.CHANNEL_SHELL)) {
//                        channel.setAgentForwarding(true);
//                        channel.setPtyType("xterm");
//                        channel.setPtyColumns(160);
//                        channel.setPtyLines(50);
//                        channel.setPtyWidth(0);
//                        channel.setPtyHeight(0);
//                        Map<PtyMode, Integer> ptyModes = new HashMap<>();
//                        ptyModes.put(PtyMode.TTY_OP_OSPEED, 38400);
//                        ptyModes.put(PtyMode.TTY_OP_ISPEED, 38400);
//                        channel.setPtyModes(ptyModes);
//                        channel.setIn(new NoCloseInputStream(System.in));
//                        channel.setOut(new NoCloseOutputStream(System.out));
//                        channel.setErr(new NoCloseOutputStream(System.err));
//                        channel.open();
//                        System.out.println(channel.getProperties());
//                        channel.waitFor(Arrays.asList(ClientChannelEvent.CLOSED), 0);
//                    } finally {
//                        session.close(false);
//                    }
//                } finally {
//                    client.stop();
//                }
//            }
//        }
//    }
//}

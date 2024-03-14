package org.example.db.postgre;

import org.postgresql.PGConnection;
import org.postgresql.replication.PGReplicationStream;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresListener {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgresql";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PGConnection pgConnection = connection.unwrap(PGConnection.class);

            // 创建逻辑复制插槽和通道
            pgConnection.getReplicationAPI().createReplicationSlot()
                    .logical()
                    .withSlotName("my_slot")
                    .withOutputPlugin("pgoutput")
                    .make();

            // 监听逻辑
            PGReplicationStream replicationStream = pgConnection.getReplicationAPI().replicationStream()
                    .logical()
                    .withSlotName("my_slot")
                    .withSlotOption("include-xids", false)
                    .withSlotOption("skip-empty-xacts", true)
                    .withStatusInterval(10, java.util.concurrent.TimeUnit.SECONDS)
                    .start();

            while (true) {
                // 打印当前最新的 LSN
                System.out.println("Current LSN: " + replicationStream.getLastReceiveLSN());
                // 读取变更记录
                ByteBuffer change = replicationStream.readPending();
                if (change != null) {
                    // 打印变更记录
                    System.out.println("Change: " + new String(change.array()));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


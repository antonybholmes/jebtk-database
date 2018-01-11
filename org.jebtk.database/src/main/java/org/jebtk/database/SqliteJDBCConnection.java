package org.jebtk.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Sqlite connectivity through JDBC.
 * 
 * @author Antony Holmes Holmes
 *
 */
public class SqliteJDBCConnection extends JDBCConnection {

  private File mFile;

  public SqliteJDBCConnection(File file) throws SQLException {
    super(getSqliteConnection(file));

    mFile = file;

    System.err.println("Loading SQLite DB " + file);
  }

  public File getFile() {
    return mFile;
  }

  public static Connection getSqliteConnection(File file) throws SQLException {
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      throw new SQLException(e.getMessage());
    }

    try {
      return DriverManager
          .getConnection("jdbc:sqlite:" + file.getAbsolutePath());
    } catch (SQLException e) {
      throw new SQLException(e.getMessage());
    }
  }
}

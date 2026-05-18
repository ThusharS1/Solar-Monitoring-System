package com.suryashakti.solarmonitor.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile EnergyLogDao _energyLogDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `energy_log` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` TEXT NOT NULL, `generatedKwh` REAL NOT NULL, `consumedKwh` REAL NOT NULL, `netKwh` REAL NOT NULL, `savingsRupees` REAL NOT NULL, `ratePerUnit` REAL NOT NULL, `independenceScore` REAL NOT NULL, `isExportToGrid` INTEGER NOT NULL, `weatherCondition` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '37ed930b025f99c279bddb8d8d9832f6')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `energy_log`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsEnergyLog = new HashMap<String, TableInfo.Column>(10);
        _columnsEnergyLog.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnergyLog.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnergyLog.put("generatedKwh", new TableInfo.Column("generatedKwh", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnergyLog.put("consumedKwh", new TableInfo.Column("consumedKwh", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnergyLog.put("netKwh", new TableInfo.Column("netKwh", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnergyLog.put("savingsRupees", new TableInfo.Column("savingsRupees", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnergyLog.put("ratePerUnit", new TableInfo.Column("ratePerUnit", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnergyLog.put("independenceScore", new TableInfo.Column("independenceScore", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnergyLog.put("isExportToGrid", new TableInfo.Column("isExportToGrid", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEnergyLog.put("weatherCondition", new TableInfo.Column("weatherCondition", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEnergyLog = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEnergyLog = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEnergyLog = new TableInfo("energy_log", _columnsEnergyLog, _foreignKeysEnergyLog, _indicesEnergyLog);
        final TableInfo _existingEnergyLog = TableInfo.read(db, "energy_log");
        if (!_infoEnergyLog.equals(_existingEnergyLog)) {
          return new RoomOpenHelper.ValidationResult(false, "energy_log(com.suryashakti.solarmonitor.data.EnergyLog).\n"
                  + " Expected:\n" + _infoEnergyLog + "\n"
                  + " Found:\n" + _existingEnergyLog);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "37ed930b025f99c279bddb8d8d9832f6", "a5fdeb62d9038804ea15a9c116de5822");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "energy_log");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `energy_log`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(EnergyLogDao.class, EnergyLogDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public EnergyLogDao energyLogDao() {
    if (_energyLogDao != null) {
      return _energyLogDao;
    } else {
      synchronized(this) {
        if(_energyLogDao == null) {
          _energyLogDao = new EnergyLogDao_Impl(this);
        }
        return _energyLogDao;
      }
    }
  }
}

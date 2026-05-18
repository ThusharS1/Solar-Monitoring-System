package com.suryashakti.solarmonitor.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class EnergyLogDao_Impl implements EnergyLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EnergyLog> __insertionAdapterOfEnergyLog;

  public EnergyLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEnergyLog = new EntityInsertionAdapter<EnergyLog>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `energy_log` (`id`,`date`,`generatedKwh`,`consumedKwh`,`netKwh`,`savingsRupees`,`ratePerUnit`,`independenceScore`,`isExportToGrid`,`weatherCondition`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EnergyLog entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getDate());
        statement.bindDouble(3, entity.getGeneratedKwh());
        statement.bindDouble(4, entity.getConsumedKwh());
        statement.bindDouble(5, entity.getNetKwh());
        statement.bindDouble(6, entity.getSavingsRupees());
        statement.bindDouble(7, entity.getRatePerUnit());
        statement.bindDouble(8, entity.getIndependenceScore());
        final int _tmp = entity.isExportToGrid() ? 1 : 0;
        statement.bindLong(9, _tmp);
        statement.bindString(10, entity.getWeatherCondition());
      }
    };
  }

  @Override
  public Object insert(final EnergyLog log, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEnergyLog.insert(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<EnergyLog>> getAllLogs() {
    final String _sql = "SELECT * FROM energy_log ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"energy_log"}, false, new Callable<List<EnergyLog>>() {
      @Override
      @Nullable
      public List<EnergyLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfGeneratedKwh = CursorUtil.getColumnIndexOrThrow(_cursor, "generatedKwh");
          final int _cursorIndexOfConsumedKwh = CursorUtil.getColumnIndexOrThrow(_cursor, "consumedKwh");
          final int _cursorIndexOfNetKwh = CursorUtil.getColumnIndexOrThrow(_cursor, "netKwh");
          final int _cursorIndexOfSavingsRupees = CursorUtil.getColumnIndexOrThrow(_cursor, "savingsRupees");
          final int _cursorIndexOfRatePerUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "ratePerUnit");
          final int _cursorIndexOfIndependenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "independenceScore");
          final int _cursorIndexOfIsExportToGrid = CursorUtil.getColumnIndexOrThrow(_cursor, "isExportToGrid");
          final int _cursorIndexOfWeatherCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "weatherCondition");
          final List<EnergyLog> _result = new ArrayList<EnergyLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EnergyLog _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final float _tmpGeneratedKwh;
            _tmpGeneratedKwh = _cursor.getFloat(_cursorIndexOfGeneratedKwh);
            final float _tmpConsumedKwh;
            _tmpConsumedKwh = _cursor.getFloat(_cursorIndexOfConsumedKwh);
            final float _tmpNetKwh;
            _tmpNetKwh = _cursor.getFloat(_cursorIndexOfNetKwh);
            final float _tmpSavingsRupees;
            _tmpSavingsRupees = _cursor.getFloat(_cursorIndexOfSavingsRupees);
            final float _tmpRatePerUnit;
            _tmpRatePerUnit = _cursor.getFloat(_cursorIndexOfRatePerUnit);
            final float _tmpIndependenceScore;
            _tmpIndependenceScore = _cursor.getFloat(_cursorIndexOfIndependenceScore);
            final boolean _tmpIsExportToGrid;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsExportToGrid);
            _tmpIsExportToGrid = _tmp != 0;
            final String _tmpWeatherCondition;
            _tmpWeatherCondition = _cursor.getString(_cursorIndexOfWeatherCondition);
            _item = new EnergyLog(_tmpId,_tmpDate,_tmpGeneratedKwh,_tmpConsumedKwh,_tmpNetKwh,_tmpSavingsRupees,_tmpRatePerUnit,_tmpIndependenceScore,_tmpIsExportToGrid,_tmpWeatherCondition);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<EnergyLog>> getLast30() {
    final String _sql = "SELECT * FROM energy_log ORDER BY date DESC LIMIT 30";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"energy_log"}, false, new Callable<List<EnergyLog>>() {
      @Override
      @Nullable
      public List<EnergyLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfGeneratedKwh = CursorUtil.getColumnIndexOrThrow(_cursor, "generatedKwh");
          final int _cursorIndexOfConsumedKwh = CursorUtil.getColumnIndexOrThrow(_cursor, "consumedKwh");
          final int _cursorIndexOfNetKwh = CursorUtil.getColumnIndexOrThrow(_cursor, "netKwh");
          final int _cursorIndexOfSavingsRupees = CursorUtil.getColumnIndexOrThrow(_cursor, "savingsRupees");
          final int _cursorIndexOfRatePerUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "ratePerUnit");
          final int _cursorIndexOfIndependenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "independenceScore");
          final int _cursorIndexOfIsExportToGrid = CursorUtil.getColumnIndexOrThrow(_cursor, "isExportToGrid");
          final int _cursorIndexOfWeatherCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "weatherCondition");
          final List<EnergyLog> _result = new ArrayList<EnergyLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EnergyLog _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final float _tmpGeneratedKwh;
            _tmpGeneratedKwh = _cursor.getFloat(_cursorIndexOfGeneratedKwh);
            final float _tmpConsumedKwh;
            _tmpConsumedKwh = _cursor.getFloat(_cursorIndexOfConsumedKwh);
            final float _tmpNetKwh;
            _tmpNetKwh = _cursor.getFloat(_cursorIndexOfNetKwh);
            final float _tmpSavingsRupees;
            _tmpSavingsRupees = _cursor.getFloat(_cursorIndexOfSavingsRupees);
            final float _tmpRatePerUnit;
            _tmpRatePerUnit = _cursor.getFloat(_cursorIndexOfRatePerUnit);
            final float _tmpIndependenceScore;
            _tmpIndependenceScore = _cursor.getFloat(_cursorIndexOfIndependenceScore);
            final boolean _tmpIsExportToGrid;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsExportToGrid);
            _tmpIsExportToGrid = _tmp != 0;
            final String _tmpWeatherCondition;
            _tmpWeatherCondition = _cursor.getString(_cursorIndexOfWeatherCondition);
            _item = new EnergyLog(_tmpId,_tmpDate,_tmpGeneratedKwh,_tmpConsumedKwh,_tmpNetKwh,_tmpSavingsRupees,_tmpRatePerUnit,_tmpIndependenceScore,_tmpIsExportToGrid,_tmpWeatherCondition);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getLatest(final Continuation<? super EnergyLog> $completion) {
    final String _sql = "SELECT * FROM energy_log ORDER BY date DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<EnergyLog>() {
      @Override
      @Nullable
      public EnergyLog call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfGeneratedKwh = CursorUtil.getColumnIndexOrThrow(_cursor, "generatedKwh");
          final int _cursorIndexOfConsumedKwh = CursorUtil.getColumnIndexOrThrow(_cursor, "consumedKwh");
          final int _cursorIndexOfNetKwh = CursorUtil.getColumnIndexOrThrow(_cursor, "netKwh");
          final int _cursorIndexOfSavingsRupees = CursorUtil.getColumnIndexOrThrow(_cursor, "savingsRupees");
          final int _cursorIndexOfRatePerUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "ratePerUnit");
          final int _cursorIndexOfIndependenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "independenceScore");
          final int _cursorIndexOfIsExportToGrid = CursorUtil.getColumnIndexOrThrow(_cursor, "isExportToGrid");
          final int _cursorIndexOfWeatherCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "weatherCondition");
          final EnergyLog _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final float _tmpGeneratedKwh;
            _tmpGeneratedKwh = _cursor.getFloat(_cursorIndexOfGeneratedKwh);
            final float _tmpConsumedKwh;
            _tmpConsumedKwh = _cursor.getFloat(_cursorIndexOfConsumedKwh);
            final float _tmpNetKwh;
            _tmpNetKwh = _cursor.getFloat(_cursorIndexOfNetKwh);
            final float _tmpSavingsRupees;
            _tmpSavingsRupees = _cursor.getFloat(_cursorIndexOfSavingsRupees);
            final float _tmpRatePerUnit;
            _tmpRatePerUnit = _cursor.getFloat(_cursorIndexOfRatePerUnit);
            final float _tmpIndependenceScore;
            _tmpIndependenceScore = _cursor.getFloat(_cursorIndexOfIndependenceScore);
            final boolean _tmpIsExportToGrid;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsExportToGrid);
            _tmpIsExportToGrid = _tmp != 0;
            final String _tmpWeatherCondition;
            _tmpWeatherCondition = _cursor.getString(_cursorIndexOfWeatherCondition);
            _result = new EnergyLog(_tmpId,_tmpDate,_tmpGeneratedKwh,_tmpConsumedKwh,_tmpNetKwh,_tmpSavingsRupees,_tmpRatePerUnit,_tmpIndependenceScore,_tmpIsExportToGrid,_tmpWeatherCondition);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<Float> getTotalSavings(final String from) {
    final String _sql = "SELECT COALESCE(SUM(savingsRupees),0) FROM energy_log WHERE date >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, from);
    return __db.getInvalidationTracker().createLiveData(new String[] {"energy_log"}, false, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Float> getTotalGenerated(final String from) {
    final String _sql = "SELECT COALESCE(SUM(generatedKwh),0) FROM energy_log WHERE date >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, from);
    return __db.getInvalidationTracker().createLiveData(new String[] {"energy_log"}, false, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Integer> getCount() {
    final String _sql = "SELECT COUNT(*) FROM energy_log";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"energy_log"}, false, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

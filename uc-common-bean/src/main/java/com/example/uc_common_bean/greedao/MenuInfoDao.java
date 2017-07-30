package com.example.uc_common_bean.greedao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.uc_common_bean.vo.MenuInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MENU_INFO".
*/
public class MenuInfoDao extends AbstractDao<MenuInfo, Long> {

    public static final String TABLENAME = "MENU_INFO";

    /**
     * Properties of entity MenuInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MenuCode = new Property(1, Integer.class, "menuCode", false, "MENU_CODE");
        public final static Property MenuName = new Property(2, String.class, "menuName", false, "MENU_NAME");
        public final static Property MenuDesc = new Property(3, String.class, "menuDesc", false, "MENU_DESC");
        public final static Property AuthorCode = new Property(4, Integer.class, "authorCode", false, "AUTHOR_CODE");
        public final static Property AuthorName = new Property(5, String.class, "authorName", false, "AUTHOR_NAME");
        public final static Property MenuType = new Property(6, Integer.class, "menuType", false, "MENU_TYPE");
        public final static Property MenuPicurl = new Property(7, String.class, "menuPicurl", false, "MENU_PICURL");
        public final static Property Playcount = new Property(8, Long.class, "playcount", false, "PLAYCOUNT");
        public final static Property CreateTime = new Property(9, String.class, "createTime", false, "CREATE_TIME");
        public final static Property UpdateTime = new Property(10, String.class, "updateTime", false, "UPDATE_TIME");
    };


    public MenuInfoDao(DaoConfig config) {
        super(config);
    }
    
    public MenuInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MENU_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"MENU_CODE\" INTEGER," + // 1: menuCode
                "\"MENU_NAME\" TEXT," + // 2: menuName
                "\"MENU_DESC\" TEXT," + // 3: menuDesc
                "\"AUTHOR_CODE\" INTEGER," + // 4: authorCode
                "\"AUTHOR_NAME\" TEXT," + // 5: authorName
                "\"MENU_TYPE\" INTEGER," + // 6: menuType
                "\"MENU_PICURL\" TEXT," + // 7: menuPicurl
                "\"PLAYCOUNT\" INTEGER," + // 8: playcount
                "\"CREATE_TIME\" TEXT," + // 9: createTime
                "\"UPDATE_TIME\" TEXT);"); // 10: updateTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MENU_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MenuInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer menuCode = entity.getMenuCode();
        if (menuCode != null) {
            stmt.bindLong(2, menuCode);
        }
 
        String menuName = entity.getMenuName();
        if (menuName != null) {
            stmt.bindString(3, menuName);
        }
 
        String menuDesc = entity.getMenuDesc();
        if (menuDesc != null) {
            stmt.bindString(4, menuDesc);
        }
 
        Integer authorCode = entity.getAuthorCode();
        if (authorCode != null) {
            stmt.bindLong(5, authorCode);
        }
 
        String authorName = entity.getAuthorName();
        if (authorName != null) {
            stmt.bindString(6, authorName);
        }
 
        Integer menuType = entity.getMenuType();
        if (menuType != null) {
            stmt.bindLong(7, menuType);
        }
 
        String menuPicurl = entity.getMenuPicurl();
        if (menuPicurl != null) {
            stmt.bindString(8, menuPicurl);
        }
 
        Long playcount = entity.getPlaycount();
        if (playcount != null) {
            stmt.bindLong(9, playcount);
        }
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(10, createTime);
        }
 
        String updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindString(11, updateTime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MenuInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer menuCode = entity.getMenuCode();
        if (menuCode != null) {
            stmt.bindLong(2, menuCode);
        }
 
        String menuName = entity.getMenuName();
        if (menuName != null) {
            stmt.bindString(3, menuName);
        }
 
        String menuDesc = entity.getMenuDesc();
        if (menuDesc != null) {
            stmt.bindString(4, menuDesc);
        }
 
        Integer authorCode = entity.getAuthorCode();
        if (authorCode != null) {
            stmt.bindLong(5, authorCode);
        }
 
        String authorName = entity.getAuthorName();
        if (authorName != null) {
            stmt.bindString(6, authorName);
        }
 
        Integer menuType = entity.getMenuType();
        if (menuType != null) {
            stmt.bindLong(7, menuType);
        }
 
        String menuPicurl = entity.getMenuPicurl();
        if (menuPicurl != null) {
            stmt.bindString(8, menuPicurl);
        }
 
        Long playcount = entity.getPlaycount();
        if (playcount != null) {
            stmt.bindLong(9, playcount);
        }
 
        String createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindString(10, createTime);
        }
 
        String updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindString(11, updateTime);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MenuInfo readEntity(Cursor cursor, int offset) {
        MenuInfo entity = new MenuInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // menuCode
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // menuName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // menuDesc
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // authorCode
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // authorName
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // menuType
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // menuPicurl
            cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8), // playcount
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // createTime
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10) // updateTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MenuInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMenuCode(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setMenuName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMenuDesc(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAuthorCode(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setAuthorName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMenuType(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setMenuPicurl(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPlaycount(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
        entity.setCreateTime(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setUpdateTime(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MenuInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MenuInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
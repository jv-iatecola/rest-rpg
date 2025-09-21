-- ========================================
-- Author:      jv-iatecola
-- Created:     2025-09-06
-- ========================================
-- Description:
-- - This file assumes an active connection to the target database.
-- - Modify with caution; production impact possible.
-- ========================================

CREATE TABLE characters (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL,
    character_class VARCHAR(20) NOT NULL
);
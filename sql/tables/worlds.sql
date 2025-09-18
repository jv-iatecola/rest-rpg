-- ========================================
-- Author:      jv-iatecola
-- Created:     2025-09-14
-- ========================================
-- Description:
-- - This file assumes an active connection to the target database.
-- - Modify with caution; production impact possible.
-- ========================================

CREATE TABLE worlds (
    id SERIAL PRIMARY KEY,
    mode VARCHAR(6) NOT NULL,
    character_uuid_fk INTEGER UNIQUE NOT NULL
);
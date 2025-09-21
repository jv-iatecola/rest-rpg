-- ========================================
-- Author:      jv-iatecola
-- Created:     2025-09-17
-- ========================================
-- Description:
-- - This file assumes an active connection to the target database.
-- - Modify with caution; production impact possible.
-- ========================================

CREATE OR REPLACE PROCEDURE add_world (p_mode VARCHAR(6), p_character_id INTEGER)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO worlds (mode, character_id)
VALUES (p_mode, p_character_id);
END;
$$;
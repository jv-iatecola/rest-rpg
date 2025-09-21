-- ========================================
-- Author:      jv-iatecola
-- Created:     2025-09-17
-- ========================================
-- Description:
-- - This file assumes an active connection to the target database.
-- - Modify with caution; production impact possible.
-- ========================================

CREATE OR REPLACE FUNCTION get_world_id (p_character_id INTEGER)
RETURNS INT
LANGUAGE plpgsql
AS $$
DECLARE
    v_id INT;
BEGIN
    SELECT id
    INTO v_id
    FROM worlds
    WHERE character_id = p_character_id;

    RETURN v_id;
END;
$$;


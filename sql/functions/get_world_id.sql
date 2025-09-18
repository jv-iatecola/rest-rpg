-- ========================================
-- Author:      jv-iatecola
-- Created:     2025-09-17
-- ========================================
-- Description:
-- - This file assumes an active connection to the target database.
-- - Modify with caution; production impact possible.
-- ========================================

CREATE OR REPLACE FUNCTION get_world_id (p_character_uuid_fk INTEGER)
RETURNS INT
LANGUAGE plpgsql
AS $$
DECLARE
    v_id INT;
BEGIN
    SELECT id
    INTO v_id
    FROM worlds
    WHERE character_uuid_fk = p_character_uuid_fk;

    RETURN v_id;
END;
$$;


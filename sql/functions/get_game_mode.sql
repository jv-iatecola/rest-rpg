-- ========================================
-- Author:      jv-iatecola
-- Created:     2025-10-20
-- ========================================
-- Description:
-- - This file assumes an active connection to the target database.
-- - Modify with caution; production impact possible.
-- ========================================

CREATE OR REPLACE FUNCTION get_game_mode (p_game_id INTEGER)
RETURNS VARCHAR
LANGUAGE plpgsql
AS $$
DECLARE
    v_game_mode VARCHAR;
BEGIN
    SELECT mode
    INTO v_game_mode
    FROM worlds
    WHERE id = p_game_id;

    RETURN v_game_mode;
END;
$$;

-- ========================================
-- Author:      jv-iatecola
-- Created:     2025-09-10
-- ========================================
-- Description:
-- - This file assumes an active connection to the target database.
-- - Modify with caution; production impact possible.
-- ========================================

CREATE OR REPLACE FUNCTION get_character_id (p_name VARCHAR)
RETURNS INT
LANGUAGE plpgsql
AS $$
DECLARE
    v_id INT;
BEGIN
    SELECT id
    INTO v_id
    FROM characters
    WHERE name = p_name;

    RETURN v_id;
END;
$$;


// hooks/useFetch.js
import { useState, useEffect } from 'react';

const useFetch = (apiFunc) => {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await apiFunc();
                setData(result);
            } catch (err) {
                setError(err);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [apiFunc]);

    return { data, error, loading };
};

export default useFetch;

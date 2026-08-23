import pandas as pd

def pivotTable(weather: pd.DataFrame) -> pd.DataFrame:
    result = weather.pivot(index='month', columns='city', values='temperature')
    result.columns.name = None
    return result